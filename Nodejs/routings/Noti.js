var express = require('express');
var router = express.Router();
const { checkToken } = require('../helpers/TokenCheck')
const { sequelize } = require('../databases/database')
const PhoneTokenModel = require('../models/PhoneToken')(sequelize)
const UserTokenModel = require('../models/UserToken')(sequelize)
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
                    message: 'Phone tồn tại'
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


/**
 * URL: http://localhost:3000/foods/getfood
 */

router.get('/getfood', async(req, res) => {
        const { tokenkey } = req.headers
        console.log(`tokkkkeeeen = ${tokenkey}`)
        const isValidToken = await checkToken({ tokenkey })
        if (isValidToken == false) {
            res.json({
                result: "failed",
                data: null,
                message: 'Token is invalid'
            })
            return;
        }
        const { page, pageNumber } = req.body
        let foundBooks = await FoodModel.findAll({
            limit: pageNumber,
            offset: pageNumber * page,
        })
        res.json({
            result: "ok",
            data: foundBooks,
            message: 'Get list of books successfully'
        })
    })
    //Them moi 1 quyen sach:
router.post('/', async(req, res) => {
    //Noi dung them moi
})

module.exports = router