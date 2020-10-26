
var admin = require('firebase-admin')
var serviceAccount = require("./serviceAccount.json");
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://finalmatch-9f4fe.firebaseio.com"
});

const sendFirebaseCloudMessage = async ({ title, data, notificationTokens }) => {
  const failedTokens = [];
  try {            
    if (notificationTokens.length == 0) {
      console.log('No notification Tokens to send');
      return []
    }    
    let response = await admin.messaging().sendMulticast({
      data,
      tokens: notificationTokens,
    })    
    if (response.failureCount > 0) {
      if (response.successCount == notificationTokens.length) {
        console.log("send all notifications successfully")
      }
      response.responses.forEach((resp, idx) => {
        if (!resp.success) {
          failedTokens.push(notificationTokens[idx]);
        }
      })
      console.log('List of tokens that caused failures: ');
      failedTokens.forEach(failedToken => {
        console.log(`{failedToken}\n`)
      })

    }    
    return failedTokens
  } catch (error) {    
    console.log('Cannot send FCM.Error =' + error);
    return failedTokens
  }
}

module.exports = {
    sendFirebaseCloudMessage
}
//Test this function
setInterval(async () => {
  console.log('Bat dau gui')
  await sendFirebaseCloudMessage({
    title: "haha", 
    data: {name: "Hoang", age: "20"},
    notificationTokens: [      
        "dCsu2ojlT8CLtGxq16jIW4:APA91bFgwNMoF-GIOEngzup6dGQHOUB7vn-DWmXG7zTNjkvhPnVj7QAQhtxNTKC4bEY6Im-sml-11GvWGliwnCPKrySP_4O2v0I-XHpk1XAnCin_sQzRBeXmsb08VpvIduxau3lIG6Nf",
        "eedXczHQSgyNefhVgDN14X:APA91bFzMlv04u4E1ca-M-lA2cb2oKqRHCy1t7qNYkilGgDyBZox3NmJFzO4uf2i4QTnMtB0JtnxZwXVBqrvTmMzsa-w8IE6oaHNbOsl0tv_kfT5TUSMON6tHJW2EG17Ti-D2E14i6BB",
        "d4rD3ifzSqiD4BYovkyjPB:APA91bG5Kegez3iPxOVSpJjv-xX18h7koyw20M5C-ay31hwyQBYAarS5Acs-LG2_nrcXW_m2SkBOMy30-kZ4LsCWjwKQCigpVNqHIef2Ze5WawON93H2BQ1zul5EE9rgnbWEpyRFcSFR"
    ]
  })
  console.log('Da gui xong')
}, 5000)