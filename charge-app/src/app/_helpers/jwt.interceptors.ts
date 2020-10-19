import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AuthenticationService } from '../authentication.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthenticationService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // add authorization header with jwt token if available
        let curuser=sessionStorage.getItem('username');
        let curtoken=sessionStorage.getItem('token');
        let currole=sessionStorage.getItem('role');
       // let currentUser = this.authenticationService.username;
        if (curuser && curtoken) {
            request = request.clone({
                setHeaders: {
                    Role: `Role ${currole}`,
                    Authorization: `Bearer ${curtoken}`
                }
            });
        }
        return next.handle(request);
    }
}