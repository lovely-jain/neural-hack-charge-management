
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddBankComponent } from './add-bank/add-bank.component';
import { UpdateBankComponent } from './update-bank/update-bank.component';
import { LoginComponent } from './login/login.component';
import { ListBankComponent } from './list-bank/list-bank.component';
import { ReactiveFormsModule, FormsModule  } from '@angular/forms';
import { DeleteComponent } from './delete/delete.component';
import { ApproveComponent } from './approve/approve.component';
import { RejectComponent } from './reject/reject.component';
import { PendingComponent } from './pending/pending.component';
import {JwtInterceptor} from './_helpers/jwt.interceptors';
import { UpdateRuleComponent } from './update-rule/update-rule.component';

//import {} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    AddBankComponent,
    UpdateBankComponent,
    LoginComponent,
    ListBankComponent,
    DeleteComponent,
    PendingComponent,
      ApproveComponent,
      RejectComponent,
      UpdateRuleComponent,
      UpdateRuleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    
  ],
  providers: [
    {  
      provide:HTTP_INTERCEPTORS, useClass:JwtInterceptor, multi:true 
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }