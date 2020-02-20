const functions = require('firebase-functions');
const admin = require('firebase-admin');

admin.initializeApp();

exports.myFunction = functions.firestore
  .document('users/{user_id}/items/{item_id}')
  .onWrite((change, context) => { 

      const newValue = change.after.data();

      admin.firestore().collection('inventoryItems').doc(newValue.title).set({
          title: newValue.title
      })
   });
