var express = require('express');
var router = express.Router();
const { checkToken } = require('../helpers/TokenCheck')
const { sequelize } = require('../databases/database')
const StoreModel = require('../models/Store')(sequelize)
const ImageModel = require('../models/Image')(sequelize)
const { validateString } = require('../validations/validate')
const { validationResult } = require('express-validator')
const { Op } = require("sequelize");

ImageModel.hasMany(StoreModel, { foreignKey: 'id' })
StoreModel.belongsTo(ImageModel, { foreignKey: 'imageId' })

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
    const { search } = req.body
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
            include: {
                model: ImageModel,
                as: "Image_model"
            }
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

module.exports = router