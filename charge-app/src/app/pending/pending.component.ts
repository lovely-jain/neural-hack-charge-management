import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Add } from '../add';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pending',
  templateUrl: './pending.component.html',
  styleUrls: ['./pending.component.css']
})
export class PendingComponent implements OnInit {

  constructor(private _service: UserService, private _router:Router) { }
  public collection: any; 
  rule = new Add();


  ngOnInit(): void {
    if(sessionStorage.getItem('role')!="approver" && sessionStorage.getItem('role')!="creator" )
    {
      
      alert("Access Not Allowed");
      this._router.navigate(['/']);
    }
    else{
    this._service.displayPendingListFromRemote(this.rule).subscribe(
      data => {
        this.collection=data;
      },
      error => {
        if(error.status==401)
        {
          console.log("user not logged in");
          this._router.navigate(['/login']);
        }
        else{
          alert(" some error occcurred!! ")
        }
      }
    )
  }
  }


}
