import { CommonService } from '../common.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})


export class AdminComponent implements OnInit {
  public collection: any;
  public flag:boolean=false;
  alert: boolean = false;
  public adminLogIn: boolean=false;
  

  constructor(private commonService: CommonService) { }

  login = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),

  });

  ngOnInit(): void {
    this.commonService.getAdminList().subscribe((result) => {
      this.collection = result;
      // console.log(this.collection);
    });
  }

  onSubmit() {
    this.check(this.login.value);
    this.login.reset();
  }

  check(result){
    this.flag=false;
    for(let i=0;i<this.collection.length;i++){
      if (result["email"] === this.collection[i]["email"] && result["password"] === this.collection[i]["password"])
      {console.log(result["email"] +" "+ this.collection[i]["email"]);
      console.log("Yes");
      this.flag=true;
        this.alert = true;
        this.commonService.adminLogIn=true;
      break;}
      }


    if (this.flag === false) {
      console.log("No");
      alert("Wrong Email or Password");
    }


  }

}
