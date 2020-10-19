import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

//import { environment } from '@environments/environment';
import { User } from './user';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<User>;
    public username: Observable<User>;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(sessionStorage.getItem('username')));
        this.username = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    login(user1: User) {
        return this.http.post<any>("http://localhost:8083/login", user1)
            .pipe(map(user => {
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                sessionStorage.setItem('username', JSON.stringify(user1.email));
                let tokenstr=user.jwttoken;
                sessionStorage.setItem('token', tokenstr);
               // console.log(user.jwttoken);
                sessionStorage.setItem('role',user.role)
               // sessionStorage.setItem('role',user.role);
                this.currentUserSubject.next(user);
               // console.log("in login  angular")
                return user;
            }));
    }

    logout() {
    
        sessionStorage.removeItem('username');
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('role');
        this.currentUserSubject.next(null);
        
    }
}