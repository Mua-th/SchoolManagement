package org.example.repositories;

import org.example.config.Database;

public class SuperRepo {

  protected static Database myDatabase ;

  //implement the singleton pattern

  private static SuperRepo instance ;

  public static SuperRepo getInstance(Database myDatabase){
    if(instance==null){
      instance = new SuperRepo(myDatabase) ;
    }
    return instance ;
  }

  public SuperRepo(Database myDatabase) {
    this.myDatabase = myDatabase;
  }
}
