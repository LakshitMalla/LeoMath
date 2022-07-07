import 'package:flutter/material.dart';

class SizeConfig {
  static late double heightRatio;
  static late double widthRatio;
  init(BuildContext context) {
    var mediaQuerySize = MediaQuery.of(context).size;
    heightRatio = mediaQuerySize.height / 968;
    widthRatio = mediaQuerySize.width / 466;
  }
}

getProportionateHeight(double height) {
  return height * SizeConfig.heightRatio;
}

getProportionateWidth(double width) {
  return width * SizeConfig.widthRatio;
}
