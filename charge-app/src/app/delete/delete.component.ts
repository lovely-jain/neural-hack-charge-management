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
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent implements OnInit {
  alertmsg:boolean=false;
  rulecode = new Code();

  constructor(private _service: UserService, private _router: Router) { }

  delete = new FormGroup({
    code: new FormControl(''),

  });

  ngOnInit(): void {
    if(sessionStorage.getItem('role')!="admin")
    {
      this._router.navigate(['/']);
    } 
  }

  get Code() {
    return this.delete.get('code');
  }

  deleteRule() {
    this.rulecode.code = this.Code.value;
    console.log(this.rulecode.code)
    this._service.deleteRuleFromRemote(this.rulecode).subscribe(
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
          alert("ACCESS NOT ALLOWED !")
        }
        else{
          this.alertmsg=true;
      }
    }
    )
}
}