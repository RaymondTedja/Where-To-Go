/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  TOSHIBA
 * Created: 05/09/2016
 */

create table accountWTG (
    username varchar(255) not null primary key,
    password varchar(255) not null,
    email varchar(255) not null,    
    userType varchar(255) not null
    
);

create table Account(
        username varchar(255) NOT NULL PRIMARY KEY,
        password varchar(255) NOT NULL
        fullname varchar(255) NOT NULL,
        email varchar (255) NOT NULL);

create table tourist_Spot_WTG (
    spotName varchar(255) not null primary key,
    spotType varchar(255) not null,
    spotState varchar(255) not null,    
    spotCity varchar(255) not null
);

Create table review_WTG(
    reviewID int not null primary key
             GENERATED ALWAYS AS IDENTITY
            (START WITH 1, INCREMENT BY 1),
    creator varchar(255) not null ,
    rating int not null,
    story varchar(255),    
    theSpot varchar(255) not null);

create view jdbcrealm_user (username, password) as
select username, password
from accountWTG;

create view jdbcrealm_group (username, groupname) as
select username, userType
from accountWTG;
