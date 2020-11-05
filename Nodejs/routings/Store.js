var express = require('express');
var router = express.Router();
const { checkToken } = require('../helpers/TokenCheck')
const { sequelize } = require('../databases/database')
const StoreModel = require('../models/Store')(sequelize)
const ImageModel = require('../models/Image')(sequelize)
const RatingModel = require('../models/RatingStore')(sequelize)
const CommentStoreModel = require('../models/StoreComment')(sequelize)
const { validateString } = require('../validations/validate')
const { validationResult } = require('express-validator')
const { Op } = require("sequelize");

ImageModel.hasMany(StoreModel, { foreignKey: 'imageId' })
StoreModel.belongsTo(ImageModel, { foreignKey: 'imageId' })

StoreModel.hasMany(RatingModel, { foreignKey: 'storeId' })
RatingModel.belongsTo(StoreModel, { foreignKey: 'storeId' })

/**
 * URL: http://localhost:3000/store/search
 */
router.post('/search', validateString(), async(req, res) => {
    //validate du lieu tu client gui len
    const { tokenkey } = req.headers
    const isValidToken = await checkToken({ tokenkey })
    if (isValidToken == false) {
        res.json({
            result: "failed",
            data: null,
            message: 'Token is invalid'
        })
        return;
    }
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        res.status(422).json({
            result: 'failed',
            data: {},
            message: 'Validation input error',
            errors: errors.errors
        });
        return;
    }
    const { search, page, pageNumber } = req.body
    try {
        let foundStore = await StoreModel.findAll({
            where: {
                [Op.or]: [{
                    name: {
                        [Op.substring]: search
                    }
                }, {
                    address: {
                        [Op.substring]: search
                    }
                }, {
                    phoneNumber: {
                        [Op.substring]: search
                    }
                }]
            },
            include: [{
                    model: ImageModel,
                    as: "Image_model",
                    attributes: ['imageURL']
                },
                {
                    model: RatingModel,
                    as: "RatingStore_models",
                }
            ],
            limit: pageNumber,
            offset: pageNumber * page,
        })

        res.json({
            result: 'ok',
            data: foundStore,
            message: 'Find successfully'
        })
    } catch (exception) {
        res.status(500).json({
            result: 'failed 500',
            data: {},
            message: `Error details: ${exception.toString()}`,
            errors: []
        })
    }
})



/**
 * URL: http://localhost:3000/store/detailStore
 */
router.post('/detailStore', async(req, res) => {
    //validate du lieu tu client gui len
    const { tokenkey } = req.headers
    const isValidToken = await checkToken({ tokenkey })
    if (isValidToken == false) {
        res.json({
            result: "TK01",
            data: null
        })
        return;
    }
    const { id } = req.body
    try {
        let foundStore = await StoreModel.findAll({
            where: {
                id: {
                    [Op.eq]: id
                }
            },
            include: [{
                model: ImageModel,
                as: "Image_model",
                attributes: ['imageURL']
            }]
        })
        if (foundStore.length == 0) {
            res.json({
                result: 'ST01',
                data: null
            })
            return
        }
        let result5 = await RatingModel.findAndCountAll({
            where: {
                storeId: {
                    [Op.eq]: foundStore[0].id
                },
                rating: {
                    [Op.eq]: 5
                }
            }
        })
        let result4 = await RatingModel.findAndCountAll({
            where: {
                storeId: {
                    [Op.eq]: foundStore[0].id
                },
                rating: {
                    [Op.eq]: 4
                }
            }
        })
        let result3 = await RatingModel.findAndCountAll({
            where: {
                storeId: {
                    [Op.eq]: foundStore[0].id
                },
                rating: {
                    [Op.eq]: 3
                }
            }
        })
        let result2 = await RatingModel.findAndCountAll({
            where: {
                storeId: {
                    [Op.eq]: foundStore[0].id
                },
                rating: {
                    [Op.eq]: 2
                }
            }
        })
        let result1 = await RatingModel.findAndCountAll({
            where: {
                storeId: {
                    [Op.eq]: foundStore[0].id
                },
                rating: {
                    [Op.eq]: 1
                }
            }
        })
        let newComment = await CommentStoreModel.findAll({
            where: {
                storeId: {
                    [Op.eq]: foundStore[0].id
                }
            },
            limit: 3,
            order: [
                ['id', 'DESC']
            ]
        })

        res.json({
            result: 'SC',
            data: {
                foundStore,
                result5,
                result4,
                result3,
                result2,
                result1,
                newComment
            }
        })

    } catch (exception) {
        res.status(500).json({
            result: 'E500',
            data: null,
            message: `Error details: ${exception.toString()}`,
        })
    }
})

module.exports = router