/*
    import file
*/
const { MAXMUM_FILE_SIZE, PORT } = require('./constants/constants')
const express = require('express')
const app = express()

const bodyParser = require('body-parser')
app.use(bodyParser.urlencoded({ extended: false }))
const fileUpload = require('express-fileupload')
app.use(bodyParser.json())


/*
    setting
*/
app.use(fileUpload({
    limits: { fileSize: MAXMUM_FILE_SIZE * 1024 * 1024 }, //Maximum = 50 MB  
}))


/*
    call API rourter
*/

const UserRouter = require('./routings/User')
app.use('/users', UserRouter)
const FoodRouter = require('./routings/Food')
app.use('/foods', FoodRouter)
const StoreRouter = require('./routings/Store')
app.use('/store', StoreRouter)
const NotiRouter = require('./routings/Noti')
app.use('/noti', NotiRouter)
const RatingRouter = require('./routings/Rating')
app.use('/rating', RatingRouter)


/*
    listening
*/
app.listen(PORT, () => {
    console.log(`Server listening at http://localhost:${PORT}`)
})