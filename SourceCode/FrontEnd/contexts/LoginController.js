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

