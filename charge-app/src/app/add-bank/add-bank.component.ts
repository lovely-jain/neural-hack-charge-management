import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { Add } from '../add';


@Component({
  selector: 'app-add-bank',
  templateUrl: './add-bank.component.html',
  styleUrls: ['./add-bank.component.css']
})
export class AddBankComponent implements OnInit {

  constructor(private _service: UserService, private _router: Router) { }
  alert:boolean=false;
  alerterror:boolean=true;
  rule=new Add();

  add = new FormGroup({
    category: new FormControl(''),
    operationType: new FormControl(''),
    conditionType: new FormControl(''),
    limitFrom: new FormControl(''),
    limitTo: new FormControl(''),
    feesType: new FormControl(''),
    feesValue: new FormControl(''),
  });


  ngOnInit(): void {
    if(sessionStorage.getItem('role')!="creator")
    {
      this._router.navigate(['/']);
    } 
  }

  get Category() {
    return this.add.get('category');
  }

  get OperationType() {
    return this.add.get('operationType');
  }

  get ConditionType() {
    return this.add.get('conditionType');
  }

  get LimitFrom() {
    return this.add.get('limitFrom');
  }

  get LimitTo() {
    return this.add.get('limitTo');
  }

  get FeesType() {
    return this.add.get('feesType');
  }

  get FeesValue() {

    return this.add.get('feesValue');
  }



  addRule(){
    this.rule.category = this.Category.value;
    this.rule.operationType = this.OperationType.value;
    this.rule.conditionType = this.ConditionType.value;
    this.rule.limitFrom = this.LimitFrom.value;
    this.rule.limitTo = this.LimitTo.value;
    this.rule.feesType = this.FeesType.value;
    this.rule.feesValue = this.FeesValue.value;
    this._service.addRuleFromRemote(this.rule).subscribe(
      data => {
        this._router.navigate(["/pendinglist"]);
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
        
          this.alerterror=true;
      }
    }
    )

  }

  closeAlert(){
    this.alert=false;
  }

}