import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';
import { Code } from './code';
import { HttpHeaders, HttpRequest, HttpResponse } from '@angular/common/http';
import { Add } from './add';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    login: boolean = false;
    adminLogin: boolean = false;
    userLogin: boolean = false;
    approverLogin: boolean = false;
    creatorLogin: boolean = false;
    paramval:string;
    paramname:string;

    urlList = "http://localhost:3000/list";

    httpOptions = {
      withCredentials:true,
      response:HttpResponse,
     headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      observe: 'response' as 'response',
    };

    constructor(private _http: HttpClient) { }
    public loginUserFromRemote(user1: User): Observable<Response> {
      //console.log("trying to call")
      return this._http.post<any>("http://localhost:8083/login", user1)
    }
    public addRuleFromRemote(rule: Add): Observable<any> {
      return this._http.post<any>("http://localhost:8083/createrule", rule)
    }
    public updatRuleDataFromRemote(user1: Add): Observable<any> {
     return this._http.post<any>("http://localhost:8083/updaterule", user1)
    }

    public updateRuleFromRemote(rulecode: Code): Observable<any> {
      this.paramval= rulecode.code
      this.paramname="code"
        return this._http.get<any>("http://localhost:8083/update",{params:{paramname:this.paramval}})
      }
    
      public deleteRuleFromRemote(rulecode: Code): Observable<any> {
        this.paramval= rulecode.code
      this.paramname="code"
        return this._http.delete<any>("http://localhost:8083/delete",{params:{paramname:this.paramval}})
      }
      public approveRuleFromRemote(rulecode: Code): Observable<any> {
        this.paramval= rulecode.code
      this.paramname="code"
        return this._http.get<any>("http://localhost:8083/approve",{params:{paramname:this.paramval}})
      }

      public rejectRuleFromRemote(rulecode: Code): Observable<any> {
        this.paramval= rulecode.code
      this.paramname="code"
        return this._http.delete<any>("http://localhost:8083/reject",{params:{paramname:this.paramval}})
      }
      
      public displayListFromRemote(user1: Add): Observable<any> {

        return this._http.get<any>("http://localhost:8083/list")
    }
    
  getAll() {
    return this._http.get<User[]>(`http://localhost:8083/users`),this.httpOptions;
}
    public displayPendingListFromRemote(user1: Add): Observable<any> {
      return this._http.get<any>("http://localhost:8083/pendinglist")
    }
  }
