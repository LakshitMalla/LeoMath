import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

enum GENDER { BOY, GIRL }

class UserProvider extends ChangeNotifier {
  String name = "";
  int age = 5;
  GENDER gender = GENDER.BOY;

  setName(_) {
    name = _;
  }

  setGender(_) {
    gender = _;
  }

  setAge(_) {
    age = _;
  }
}
