
import "./App.css"
import SLogin from "./SpotifyLogin"
import Dashboard from "./Dashboard"
import React from "react"

const code = new URLSearchParams(window.location.search).get("code")

function runner() {
  return code ? <Dashboard code={code} /> : <SLogin />
}

export default runner