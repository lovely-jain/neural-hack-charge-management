import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  urlRules ="http://localhost:3000/rules";
  urlAdmin ="http://localhost:3000/admin";
  urlUser = "http://localhost:3000/users";


  constructor(private _http: HttpClient) {}

  getBankList() {
    return this._http.get(this.urlRules)
  }

  getAdminList(){
    return this._http.get(this.urlAdmin)
  }

  getUserList() {
    return this._http.get(this.urlUser)
  }

  addRule(data){
    return this._http.post(this.urlRules,data)
  }

  addUser(data) {
    return this._http.post(this.urlUser, data)
  }

  adminLogIn:boolean =false;
  
}
