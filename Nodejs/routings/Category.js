var express = require('express');
var router = express.Router()
const { checkToken } = require('../helpers/TokenCheck')
const { sequelize } = require('../databases/database')
const ImageModel = require('../models/Image')(sequelize)
const CategoryModel = require('../models/Category')(sequelize)
const FoodModel = require('../models/Food')(sequelize)
const StoreModel = require('../models/Store')(sequelize)
const{Op}=require("sequelize");
const Store = require('../models/Store');

ImageModel.hasMany(CategoryModel, {foreignKey: 'imageId'})
CategoryModel.belongsTo(ImageModel, { foreignKey: 'imageId'})

ImageModel.hasMany(FoodModel, {foreignKey: 'imageId'})
FoodModel.belongsTo(ImageModel, {foreignKey: 'imageId'})

StoreModel.hasMany(FoodModel, { foreignKey: 'storeId' })
FoodModel.belongsTo(StoreModel, { foreignKey: 'storeId' })

router.post('/getAllCategory', async(req, res) =>{
    const {tokenkey} = req.headers
    const isValidToken = await checkToken({tokenkey})
    if(isValidToken == false){
        res.json({
            result: "TK01",
            data: null
        })
        return
    }

    try{
        let foundCategory= await CategoryModel.findAll({
            
            include:[{
                model: ImageModel,
                as: "Image_model",
                attributes: ['imageUrl']
            }]
        })
        res.json({
            result: 'SC',
            data: foundCategory 
        })
    }catch (exception){
        res.status(500).json({
            result: 'E500',
            data : null,
            message: `Error details: ${exception.toString()}`
        })
    }
})

router.post('/getFoodByCatId', async(req,res) =>{
    const {tokenkey} = req.headers
    const isValidToken = await checkToken({tokenkey})
    if(!isValidToken){
        res.json({
            result: "TK01",
            data: null
        })
    }
    const{id} = req.body
    try{
        let foundFoods = await FoodModel.findAll({
            where: {
                catId: {
                    [Op.eq]: id
                }
            },
            include: [{
                model: ImageModel,
                as: "Image_model",
                attributes: ["imageUrl"]
            },
            {
               model: StoreModel,
               as: "Store_model",
               attributes: ["id"] 
            }]
        })
    }
})
module.exports = router