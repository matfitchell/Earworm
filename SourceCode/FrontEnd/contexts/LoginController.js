import * as testData from './App.js'
testData.testData; // You can now access the array

function test(userName, password) {
    
    for (let i = 0; i < testData.length; i++) {
        if (userName == testData[i].userName) {
            authenticate(i, password);
        }
    }
}

function authenticate(userVal, testPass) {

    if (testPass == testData[userVal].password) {
        console.log("yes");
    }
    else {
        console.log("no");
    }
}

function createUser(userName, password, firstName, lastName, email, birthday){
    let user = {userName: this.user, password: this.password,
    firstName : this.firstName, lastName: this.lastName, email : this.email, birthday : this.birthday}
}


//create a user

//append user to list

//push the list to the java

//write a csv


