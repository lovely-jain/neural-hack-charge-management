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
import { Add } from '../add';

@Component({
  selector: 'app-update-bank',
  templateUrl: './update-bank.component.html',
  styleUrls: ['./update-bank.component.css']
})
export class UpdateBankComponent implements OnInit {
  alertmsg: boolean = false;
  rulecode= new Code();
  addrule= new Add();
  public collection:any;
  constructor(private _service: UserService, private _router: Router) { }
  update = new FormGroup({
    code: new FormControl(''),
    name: new FormControl(''),
  });

  ngOnInit(): void {
    if(sessionStorage.getItem('role')!="admin")
    {
      
      alert("Access Not Allowed");
      this._router.navigate(['/']);
    }
  }
  get Code() {
    return this.update.get('code');
  }
  get Name()
  {
    return this.update.get('name');
  }
  updateRule(){
    this.rulecode.code=this.Code.value;
    this._service.updateRuleFromRemote(this.rulecode).subscribe(
      data => {
      this.addrule=data;
      this.addrule.code=this.rulecode.code;
      sessionStorage.setItem('ruledata',JSON.stringify(this.addrule));
      this._router.navigate(['/updateRule']);
      this.collection=data;
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
        }
        else{
          this.alertmsg=true;
        }
      }
    ) 
  }
}
