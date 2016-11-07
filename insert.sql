/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  TOSHIBA
 * Created: 05/09/2016
 */

insert into accountWTG (username, password, email, userType) values
    ('raymond', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Raymond_Tedja88@hotmail.co.id', 'admin');
insert into accountWTG (username, password, email, userType) values
    ('frenskein', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'frenskentein@live.com', 'user');
insert into accountWTG (username, password, email, userType) values
    ('xer', 'ba44f16d7846c371513cbdbbc60fe93aeb402201310951d54291f976b820f9f9', 'xer@google.com', 'user');

insert into tourist_Spot_WTG (spotName, spotType, spotState, spotCity) values
    ('Blue Mountain', 'Mountain Range', 'NSW', 'Katoomba');
insert into tourist_Spot_WTG (spotName, spotType, spotState, spotCity) values
    ('Opera House', 'arts centre', 'NSW', 'Sydney');

insert into Review_WTG (Creator, Rating, Story, TheSpot) values
    ('raymond', 3 , 'Nice', 'Blue Mountain');
insert into Review_WTG (Creator, Rating, Story, TheSpot) values
    ('Snow', 5 , null , 'Opera House');