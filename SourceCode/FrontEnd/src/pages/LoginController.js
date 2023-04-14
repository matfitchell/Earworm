

function test(userName, password) {
    console.log("attempting to authenticate: " + userName + " / " + password);

    authenticate(userName, password);
}

function authenticate(user, pass) {
    // if (user == "test1" && pass == "test2") {
    //     console.log("yes");
    // }
    // else {
    //     console.log("no");
    // }
    
    connection = new Connection();
    connection.query("SELECT username, password FROM users WHERE user = " + userName);
    username, _password = connection.fetch() // gets result from the above query

    if (username) 
    {
        if (pass == _password)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    else 
    {
        return false;
    }
}

