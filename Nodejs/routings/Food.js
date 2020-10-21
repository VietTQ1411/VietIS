var express = require('express');
var router = express.Router();
const { checkToken } = require('../helpers/TokenCheck')
const { sequelize } = require('../databases/database')
const FoodModel = require('../models/Food')(sequelize)
const { validateFoodNew } = require('../validations/validate')

/**
 * URL: http://localhost:3000/foods/addnew
 */
router.post('/addnew', validateFoodNew(), async(req, res) => {
    //validate du lieu tu client gui len    
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
    const { storeId, name, catId, price, description, imageId } = req.body
    try {
        let foundFoods = await FoodModel.findAll({
            where: {
                name: {
                    [Op.eq]: name
                },
                storeId: {
                    [Op.eq]: storeId
                }
            }
        })
        if (foundFoods.length > 0) {
            res.json({
                result: 'failed',
                data: {},
                message: 'Food tồn tại'
            })
            return
        }

        let newFood = await FoodModel.create({
            storeId,
            name,
            catId,
            price,
            description,
            imageId
        })
        await newFood.save()

        res.json({
            result: 'ok',
            data: newFood,
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
 * URL: http://books/getfood
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
        let foundBooks = await FoodModel.findAll()
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