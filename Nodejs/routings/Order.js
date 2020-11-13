var express = require('express');
var router = express.Router();
const { checkToken } = require('../helpers/TokenCheck')
const { sequelize } = require('../databases/database')
const OrderModel = require('../models/Order')(sequelize)
const OrderDetail = require('../models/OrderDetail')(sequelize)
const ImageModel = require('../models/Image')(sequelize)
const UserModel = require('../models/User')(sequelize)
const CommentStoreModel = require('../models/StoreComment')(sequelize)
const { validateString } = require('../validations/validate')
const { validationResult } = require('express-validator')
const { Op, INTEGER } = require("sequelize");


OrderModel.hasMany(OrderDetail, { foreignKey: 'orderId' })
OrderDetail.belongsTo(OrderModel, { foreignKey: 'orderId' })

ImageModel.hasMany(OrderModel, { foreignKey: 'imageId' })
OrderModel.belongsTo(ImageModel, { foreignKey: 'imageId' })

/**
 * URL: http://localhost:3000/order/getall
 */
router.post('/getall', validateString(), async(req, res) => {
    //validate du lieu tu client gui len
    const { tokenkey } = req.headers
    const isValidToken = await checkToken({ tokenkey })
    if (isValidToken == false) {
        res.json({
            result: "TK01"
        })
        return;
    }
    const { userId } = req.body
    try {

        let foundStore = await OrderModel.findAll({
            where: {
                userId: {
                    [Op.eq]: userId
                }
            },
            include: [{
                model: ImageModel,
                as: "Image_model",
                attributes: ['imageURL']
            }]
        })

        res.json({
            result: 'SC',
            data: foundStore
        })
    } catch (exception) {
        res.status(500).json({
            result: 'E500',
            message: `Error details: ${exception.toString()}`
        })
    }
})


module.exports = router