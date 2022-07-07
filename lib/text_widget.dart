import 'package:flutter/material.dart';

class TextWidget extends StatelessWidget {
  final String text;
  final Color color;
  final double size;
  final TextAlign align;
  final FontWeight fontWeight;
  const TextWidget(this.text,
      {Key? key,
      this.fontWeight = FontWeight.normal,
      this.size = 14,
      this.align = TextAlign.start,
      this.color = Colors.black})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Text(
      text,
      style: TextStyle(
          fontSize: size,
          color: color,
          fontWeight: fontWeight,
          fontFamily: "SF Pro Rounded"),
      textAlign: align,
    );
  }
}

TextStyle fontStyle(
    {fontWeight = FontWeight.normal,
    size = 14.0,
    align = TextAlign.start,
    color = Colors.black}) {
  return TextStyle(
      fontSize: size,
      color: color,
      fontWeight: fontWeight,
      fontFamily: "SF Pro Rounded");
}
