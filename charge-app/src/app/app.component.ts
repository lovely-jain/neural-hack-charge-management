import { Component } from '@angular/core';
import { CommonService } from './common.service';
import { UserService } from './user.service';
import { Router } from '@angular/router';
import {AuthenticationService} from './authentication.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'charge-app';
  constructor(private userService: UserService,private _router: Router,private auth: AuthenticationService) { }


  ngOnInit(): void {
    
  }
  display() {
    return  this._router.url=='/';
  }
  login(){
    //console.log(sessionStorage.getItem('username'))
    return this.userService.login || (sessionStorage.getItem('username'));
  }

  adminLogin(){
    return this.userService.adminLogin || (sessionStorage.getItem('role')=="admin");
  }

  userLogin() {
    return this.userService.userLogin;
  }

  approverLogin() {
    return this.userService.approverLogin || (sessionStorage.getItem('role')=="approver");
  }

  creatorLogin() {
    return this.userService.creatorLogin || (sessionStorage.getItem('role')=="creator");
  }

  logout(){
    this.auth.logout();
     console.log("logging out.....");
    this.userService.login=false;
    this.login();
    this.userService.adminLogin = false;
    this.adminLogin();
    this.userService.approverLogin = false;
    this.approverLogin();
    this.userService.creatorLogin = false;
    this.creatorLogin();
    this.userService.userLogin = false;
    this.userLogin();
    this._router.navigate(['/login']);
  }
}
