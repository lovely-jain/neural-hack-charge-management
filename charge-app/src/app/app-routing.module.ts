import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddBankComponent } from './add-bank/add-bank.component';
import { UpdateBankComponent } from './update-bank/update-bank.component';
import { ListBankComponent } from './list-bank/list-bank.component';
import { LoginComponent } from './login/login.component';
import { DeleteComponent } from './delete/delete.component';
import {RejectComponent} from './reject/reject.component';
import {ApproveComponent} from './approve/approve.component';
import {PendingComponent} from './pending/pending.component';
import { UpdateRuleComponent } from './update-rule/update-rule.component';

const routes: Routes = [
  {component: AddBankComponent,path:'add'},
  { component: UpdateBankComponent, path: 'update' },
  { component: ListBankComponent, path: 'list' },
  { component: LoginComponent, path: 'login' },
  { component: DeleteComponent, path: 'delete' },
  { component: RejectComponent, path: 'reject' },
  { component: ApproveComponent, path: 'approve' },
  { component: ApproveComponent, path: 'updaterule' },
  { component: PendingComponent, path: 'pendinglist' },
  { component: UpdateRuleComponent, path: 'updateRule'}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }