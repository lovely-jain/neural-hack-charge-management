import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { UserService } from '../user.service'
import { Router } from '@angular/router';
import { Add } from '../add';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-rule',
  templateUrl: './update-rule.component.html',
  styleUrls: ['./update-rule.component.css']
})
export class UpdateRuleComponent implements OnInit {

  constructor(private _service: UserService, private _router: Router) { }
  alert: boolean = false;
  alerterror:boolean=false;
  user1 = new Add();
  public collection: any;

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
    if(sessionStorage.getItem('role')!="admin" || sessionStorage.getItem('ruledata')==null)
    {
      this._router.navigate(['/']);
    }
    else{
        this.user1= JSON.parse(sessionStorage.getItem('ruledata'))
        console.log(this.user1);
        this.add = new FormGroup({
          category: new FormControl(this.user1.category),
          operationType: new FormControl(this.user1.operationType),
          conditionType: new FormControl(this.user1.conditionType),
          limitFrom: new FormControl(this.user1.limitFrom),
          limitTo: new FormControl(this.user1.limitTo),
          feesType: new FormControl(this.user1.feesType),
          feesValue: new FormControl(this.user1.feesValue),
        });
        sessionStorage.removeItem('ruledata');
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

  addRule() {
    this.user1.category = this.Category.value;
    this.user1.operationType = this.OperationType.value;
    this.user1.conditionType = this.ConditionType.value;
    this.user1.limitFrom = this.LimitFrom.value;
    this.user1.limitTo = this.LimitTo.value;
    this.user1.feesType = this.FeesType.value;
    this.user1.feesValue = this.FeesValue.value;

    this._service.updatRuleDataFromRemote(this.user1).subscribe(
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
          this.alerterror=true;
        }
      }
    )

  }

  closeAlert() {
    this.alert = false;
  }


}