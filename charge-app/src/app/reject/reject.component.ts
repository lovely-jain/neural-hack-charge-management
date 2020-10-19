
import { Component, OnInit } from '@angular/core';
import { CommonService } from '../common.service';
import { NgForm } from '@angular/forms';
import { FormGroup, FormControl } from '@angular/forms';
import { UserService } from '../user.service';
import { User } from '../user';
import { Router } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { rawListeners } from 'process';
import { Code } from '../code';
import {AppComponent} from '../app.component';
@Component({
  selector: 'app-reject',
  templateUrl: './reject.component.html',
  styleUrls: ['./reject.component.css']
})
export class RejectComponent implements OnInit {
  alertmsg:boolean=false;
  rulecode = new Code();

  constructor(private _service: UserService, private _router: Router) { }

  reject = new FormGroup({
    code: new FormControl(''),

  });

  ngOnInit(): void {
    if(sessionStorage.getItem('role')!="approver")
    {
      this._router.navigate(['/']);
    }
  }

  get Code() {
    return this.reject.get('code');
  }

  rejectRule() {
    this.rulecode.code = this.Code.value;
    this._service.rejectRuleFromRemote(this.rulecode).subscribe(
      data => {
        this._router.navigate(['/pendinglist']);
      },
      error => {
        if(error.status==401)
        {
          console.log("user not logged in");
          this._router.navigate(['/login']);
        }
        else if(error.status==400)
        {
          alert("ACCESS NOT ALLOWED !")
          //this._router.navigate(['/login']);
        }
        else{
          this.alertmsg=true;
      }
    }
    )
}
}
