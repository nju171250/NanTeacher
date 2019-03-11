package main

import (
	_ "hackthon_mat/server/routers"
	// C:/Users/cjh/Desktop/hackthon_mat/server/routers
	"github.com/astaxie/beego"
)

func main() {
	if beego.BConfig.RunMode == "dev" {
		beego.BConfig.WebConfig.DirectoryIndex = true
		beego.BConfig.WebConfig.StaticDir["/swagger"] = "swagger"
	}
	beego.Run()
}
