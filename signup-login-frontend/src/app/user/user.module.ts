import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginModule } from './pages/login/login.module';
import { SignupModule } from './pages/signup/signup.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HomeModule } from './pages/home/home.module';

@NgModule({
  declarations: [
 
  ],
  imports: [
    CommonModule, 
    FormsModule,
    HttpClientModule,
    HomeModule,
    SignupModule
  ],
  exports: [
    SignupModule,
    LoginModule
  ]
})
export class UserModule { }
