import 'package:flutter/material.dart';
import 'package:leomath/splash_screen.dart';
import 'package:leomath/text_widget.dart';
import 'package:leomath/user_provider.dart';
import 'package:provider/provider.dart';

main() {
  runApp(
    ChangeNotifierProvider(
      create: (context) => UserProvider(),
      child: LeoMathApp(),
    ),
  );
}

class LeoMathApp extends StatefulWidget {
  const LeoMathApp({Key? key}) : super(key: key);

  @override
  State<LeoMathApp> createState() => _LeoMathAppState();
}

class _LeoMathAppState extends State<LeoMathApp> {
  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: SplashScreen(),
    );
  }
}
