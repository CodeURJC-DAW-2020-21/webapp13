import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {


  scripts = [
    "assets/vendor/popper.min.js",
    "assets/vendor/jquery.min.js",
    "assets/vendor/bootstrap.min.js",
    "assets/vendor/perfect-scrollbar.min.js",
    "assets/vendor/dom-factory.js",
    "assets/vendor/material-design-kit.js",
    "assets/vendor/fix-footer.js",
    "assets/js/app.js",
    "assets/js/custom/text-scramble.js"
  ]

  ngOnInit(): void {
  }


  loadAPI: Promise<any>;

  constructor() {
    this.loadAPI = new Promise((resolve) => {
      this.loadScript();
      resolve(true);
    });
  }

  public loadScript() {
    this.scripts.forEach(script => {
      console.log(`Loading script => ${script}`);
      let node = document.createElement("script");
      node.src = script
      node.type = "text/javascript";
      node.async = true;
      node.charset = "utf-8";
      document.getElementsByTagName("head")[0].appendChild(node);
    });
  }


}
