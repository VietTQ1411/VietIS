var express = require('express');
var router = express.Router();
const { sendFirebaseCloudMessage } = require('../firebase-notifications/firebaseCloudMessaging')
const { sequelize } = require('../databases/database')
const PhoneTokenModel = require('../models/PhoneToken')(sequelize)
const UserTokenModel = require('../models/UserToken')(sequelize)
const NotificationModel = require('../models/Notification')(sequelize)
const { validatePhoneToken } = require('../validations/validate')
const { validationResult } = require('express-validator')
const { Op } = require("sequelize");


/**
 * URL: http://localhost:3000/noti/addTokenKey
 */
router.post('/addTokenKey', validatePhoneToken(), async(req, res) => {
    //validate du lieu tu client gui len
    const errors = validationResult(req);
    const { userId, tokenKey } = req.body
    try {
        if (!errors.isEmpty()) {
            let foundPhoneTokenModel = await PhoneTokenModel.findAll({
                where: {
                    tokenKey: {
                        [Op.eq]: tokenKey
                    }
                }
            })
            if (foundPhoneTokenModel.length > 0) {
                res.json({
                    result: 'failed',
                    message: 'PhoneToken already exsited'
                })
                return
            }
            let newPhoneToken = await PhoneTokenModel.create({
                tokenKey
            })
            await newPhoneToken.save()

            res.json({
                result: 'ok',
                message: 'Register new user successfully'
            })
            return
        }

        let foundUserToken = await UserTokenModel.findAll({
            where: {
                userId: {
                    [Op.eq]: userId
                },
                tokenKey: {
                    [Op.like]: tokenKey
                }
            }
        })
        if (foundUserToken.length > 0) {
            res.json({
                result: 'failed',
                data: {},
                message: 'user token tồn tại'
            })
            return
        }

        let newUserToken = await UserTokenModel.create({
            userId,
            tokenKey
        })
        await newUserToken.save()

        res.json({
            result: 'ok',
            message: 'Register new user successfully'
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




// // //http://192.168.1.142:3000/noti/sendNoti
router.post('/sendNotiGlobal', validatePhoneToken(), async(req, res) => {
    //validate du lieu tu client gui len    

    const { title, data } = req.body
    var jsonObj = JSON.parse(data.toString());
    let foundPhoneToken = await PhoneTokenModel.findAll({
        attributes: ['tokenKey']
    })
    let newNoti = await NotificationModel.create({
        title,
        content: jsonObj.content,
        storeid: jsonObj.storeid,
        foodid: jsonObj.foodid,
        idType: jsonObj.idType
    })
    await newNoti.save()
    const listToken = Array.from(foundPhoneToken, x => x.getDataValue('tokenKey'))
    await sendFirebaseCloudMessage({
        title: title,
        data: data,
        notificationTokens: listToken
    })
    res.json({
        result: 'ok',
        message: 'Send ok'
    })
})




/**
 * URL: http://localhost:3000/foods/getfood
 */
const getTokenKey = async() => {
    let foundPhoneToken = await PhoneTokenModel.findAll({
        attributes: ['tokenKey']
    })
    return foundPhoneToken

}
/**
 * URL: http://localhost:3000/noti/getListNoti
 */
router.post('/getListNoti',validatePhoneToken(), async(req, res)=>{
    let foundListNoti = await NotificationModel.findAll();
    res.json({
        result: 'ok',
        message: 'success',
        data: foundListNoti
    })
})



module.exports = router