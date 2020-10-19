import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Add } from '../add';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-bank',
  templateUrl: './list-bank.component.html',
  styleUrls: ['./list-bank.component.css']
})
export class ListBankComponent implements OnInit {
  constructor(private _service: UserService,private _router:Router) { }
  public collection: any;
  rule = new Add();

  ngOnInit(): void {
    if(sessionStorage.getItem('role')==null)
    {
      
      alert("Access Not Allowed")
      this._router.navigate(['/']);
    }  
    else{
    this._service.displayListFromRemote(this.rule).subscribe(
      data => {
          this.collection = data;
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
        else
        {
          alert("some error occurred!!")
         }
      }
    )
  }
  }
}
