import * as functions from 'firebase-functions';

// // Start writing Firebase Functions
// // https://firebase.google.com/docs/functions/typescript
//
// export const helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

exports.sendMessage = functions.firestore
    .document('products/{productId}')
    .onCreate((snapshot, context) => {
        const docId = context.params.productId;
        const value: any = snapshot.data();
        const name = value.name;
        const productRef = admin.firestore().collection('products').doc(docId);

        return productRef.update({message: `Nice ${title}`})
    });
































/*exports.sendMessage = functions.firestore
    .document('products/{productId}')
    .onCreate((snapshot, context) => {
        const docId = context.params.productId;
        const value: any = snapshot.data();
        const name: string = value.name;
        const productRef = admin.firestore().collection('products').doc(docId);

        return productRef.update({message: `Nice ${name}!`})
    });*/