/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

const scopes = ["tweet.read", "users.read", "tweet.write", "offline.access"]

const getAppDetails = async () => {
    const response = await fetch("app");
    return await response.json();
}


const login = (clientId, redirect_uri) => {
    var loginUrl = "https://twitter.com/i/oauth2/authorize?";
    var urlParams = new URLSearchParams();
    urlParams.append("response_type", "code");
    urlParams.append("client_id", clientId);
    urlParams.append("redirect_uri", redirect_uri);
    urlParams.append("scope", scopes.join(' '));
    urlParams.append("state", "state");
    urlParams.append("code_challenge", "challenge");
    urlParams.append("code_challenge_method", "plain");

    window.location.href = loginUrl + urlParams.toString();
}

const token = async (code) => {
    var tokenUrl = "token?";
    var urlParams = new URLSearchParams();
    urlParams.append("code", code);

    const response = await fetch(tokenUrl + urlParams.toString());

    var obj = await response.json();
    console.log(obj);
    return obj;
}

