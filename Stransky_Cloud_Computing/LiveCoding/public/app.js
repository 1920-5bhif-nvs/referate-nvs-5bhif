document.addEventListener("DOMContentLoaded", event => {
    const app = firebase.app();
})

function googleLogin(){
    const provider = new firebase.auth.GoogleAuthProvider();

    firebase.auth().signInWithPopup(provider)
        .then(result => {
            const user = result.user;
            document.write(`Hello ${user.displayName}`);
        })
}



































//DATABASE 1
/*document.addEventListener("DOMContentLoaded", event => {
    const app = firebase.app();

    const db = firebase.firestore();

    const myPost = db.collection('posts').doc('firstpost');

    myPost.get()
        .then(doc => {
            const data = doc.data();
            document.write(data.title + `<br>`)
        })
});*/

//DATABASE 2 + UPDATE
/*document.addEventListener("DOMContentLoaded", event => {
    const app = firebase.app();

    const db = firebase.firestore();

    const myPost = db.collection('posts').doc('firstpost');
    
    myPost.onSnapshot(doc => {
        const data = doc.data();
        console.log(data.title);
        document.querySelector('#title').innerHTML = data.title;
    })
})

//UPDATE + DATABASE 2
function updatePost(e){
    const db = firebase.firestore();
    const myPost = db.collection('posts').doc('firstpost');
    myPost.update({title: e.target.value}); 
}*/

//DATABASE 3 - QUERY
/*document.addEventListener("DOMContentLoaded", event => {
    const app = firebase.app();
    
    const db = firebase.firestore();
    const productsRef = db.collection('products');

	const query = productsRef.where('preis', '>=', 10);
    //const query = productsRef.orderBy('preis', 'desc').limit(2);

    query.get()
        .then(products => {
            products.forEach(doc => {
                data = doc.data();
                document.write(`${data.name} mit dem Preis von ${data.preis} <br>`);
            })
        })
});*/

//STORAGE - UPLOAD FILE
/*document.addEventListener("DOMContentLoaded", event => {
    const app = firebase.app();
});

function uploadFile(files){
    const storageRef = firebase.storage().ref();
    const logoRef = storageRef.child('logo.png');

    const file = files.item(0);

    const task = logoRef.put(file);

    task.snapshot.ref.getDownloadURL().then(function(downloadURL) {
        const url = downloadURL;
        document.querySelector("#imgUpload").setAttribute("src", url);    
      });
}*/

//AUTHENTICATION
/*document.addEventListener("DOMContentLoaded", event => {
    const app = firebase.app();
});

function googleLogin(){
    const provider = new firebase.auth.GoogleAuthProvider();

    firebase.auth().signInWithPopup(provider)
        .then(result => {
            const user = result.user;
            document.write(`Hello ${user.displayName}`);
        })
}*/