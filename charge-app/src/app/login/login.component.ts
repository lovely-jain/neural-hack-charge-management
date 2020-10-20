import { CommonService } from '../common.service';
import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { UserService } from '../user.service';
import { User } from '../user';
import { Router } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { rawListeners } from 'process';
import { first } from 'rxjs/operators';
import { AuthenticationService } from '../authentication.service';
import { IfStmt } from '@angular/compiler';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public collection: any;
  public flag: boolean = false;
  public message: string;
  alert: boolean = false;
  alert2: boolean = false;
  user1 = new User();

  get Email() {
    return this.login.get('email');
  }

  get Password() {
    return this.login.get('password');
  }
  constructor(private _service: UserService, private _router: Router,private authenticationService: AuthenticationService) { }
  login = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
    
  });

  ngOnInit(): void {
    if(sessionStorage.getItem('role')!=null)
    {
      
      this._router.navigate(['/']);
    }
  }
  closeAlert2() {
    this.alert2 = false;
  }
  loginUser() {
    this.user1.email = this.Email.value;
   this.user1.password = this.Password.value;
    this.authenticationService.login(this.user1)
    .pipe(first())
    .subscribe(
        data => {
          console.log("logged in user successfully..")
          this._service.login = true;
          if(data.role=="admin")
          this._service.adminLogin=true;
           else if(data.role=="approver")
           this._service.approverLogin=true;
            else if(data.role=="creator")
            this._service.creatorLogin=true;
          else
           this._service.userLogin=true;
            this._router.navigate(['/']);
        },
        error => {
          this.alert2=true;
        });
      }
  check(result) {
    this.flag = false;
    for (let i = 0; i < this.collection.length; i++) {
      if (result["email"] === this.collection[i]["email"] && result["password"] === this.collection[i]["password"]) {
        console.log(result["email"] + " " + this.collection[i]["email"]);
        console.log("Yes");
        this.flag = true;
        this.alert = true;
        break;
      }
    }

    if (this.flag === false) {
      console.log("No");
      alert("Wrong Email or Password");
    }
  }

  closeAlert() {
    this.alert = false;
  }

}
