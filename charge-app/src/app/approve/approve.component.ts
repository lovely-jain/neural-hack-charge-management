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

@Component({
  selector: 'app-approve',
  templateUrl: './approve.component.html',
  styleUrls: ['./approve.component.css']
})
export class ApproveComponent implements OnInit {
  alertmsg: boolean=false;
  alertmsgsuc:boolean=false;
  alerterror:boolean=false;
  rulecode = new Code();

  constructor(private _service: UserService, private _router: Router) { }

  approve = new FormGroup({
    code: new FormControl(''),

  });

  ngOnInit(): void {
    if(sessionStorage.getItem('role')!="approver")
    {
      alert("Access Not Allowed")
      this._router.navigate(['/']);
    } 
  }

  get Code() {
    return this.approve.get('code');
  }

  approveRule() {
    this.rulecode.code = this.Code.value;
    this._service.approveRuleFromRemote(this.rulecode).subscribe(
      data => {
        this._router.navigate(['/list']);
      },
      error => {
        if(error.status==401)
        {
          console.log("user not logged in");
          this._router.navigate(['/login']);
        }
        else if(error.status==400)
        {
          console.log("not allowed");
          alert("ACCESS NOT ALLOWED !")
        }
        else{ 
          this.alertmsg=true;
      }
    }
    )
}
}
