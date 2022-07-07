import 'package:flutter/material.dart';
import 'package:leomath/onboarding.dart';
import 'package:leomath/size_config.dart';
import 'package:leomath/text_widget.dart';
import 'package:leomath/user_provider.dart';
import 'package:provider/provider.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen>
    with SingleTickerProviderStateMixin {
  late AnimationController _controller;
  late UserProvider userProvider;
  TextEditingController nameController = TextEditingController();

  @override
  void initState() {
    userProvider = Provider.of<UserProvider>(context, listen: false);
    super.initState();
    _controller = AnimationController(vsync: this);
  }

  @override
  void dispose() {
    super.dispose();
    _controller.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          Image.asset(
            "assets/login_port.png",
            width: double.infinity,
            height: double.infinity,
            fit: BoxFit.cover,
          ),
          Padding(
            padding: EdgeInsets.symmetric(
                vertical: getProportionateHeight(8),
                horizontal: getProportionateWidth(32)),
            child: Column(
              children: [
                Spacer(),
                Column(
                  mainAxisSize: MainAxisSize.min,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    TextWidget(
                      "Hello,",
                      color: Color(0xFFdefc38),
                      fontWeight: FontWeight.w700,
                      size: getProportionateHeight(48),
                    ),
                    TextWidget(
                      "Leo wants to know more about you!",
                      color: Color(0xFFFFFA4C),
                      fontWeight: FontWeight.w600,
                      size: getProportionateHeight(32),
                    ),
                  ],
                ),
                SizedBox(
                  height: getProportionateHeight(32),
                ),
                ClipRRect(
                  borderRadius:
                      BorderRadius.circular(getProportionateHeight(24)),
                  child: Container(
                    color: Colors.white,
                    padding: EdgeInsets.symmetric(
                      vertical: getProportionateHeight(12),
                      horizontal: getProportionateWidth(24),
                    ),
                    child: TextField(
                      controller: nameController,
                      decoration: InputDecoration(
                        hintText: "Please enter your name",
                        border: InputBorder.none,
                        hintStyle: fontStyle(
                          size: getProportionateHeight(24),
                          fontWeight: FontWeight.w600,
                          color: Colors.black.withOpacity(0.3),
                        ),
                      ),
                      textAlign: TextAlign.center,
                      style: fontStyle(
                          size: getProportionateHeight(32),
                          fontWeight: FontWeight.w700),
                    ),
                  ),
                ),
                SizedBox(
                  height: getProportionateHeight(24),
                ),
                LeoMathButton("Login", () {
                  if (nameController.text.isEmpty) {
                    const snackBar = SnackBar(
                      content: Text('Oops! Please enter your name'),
                    );
                    ScaffoldMessenger.of(context).showSnackBar(snackBar);
                  } else {
                    userProvider.setName(nameController.text);
                    Navigator.of(context).push(
                      MaterialPageRoute(
                        builder: (context) => OnBoardingScreen(),
                      ),
                    );
                  }
                  //navigate to next screen
                }),
                const Spacer(),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

class LeoMathButton extends StatelessWidget {
  final String text;
  final Function() onTap;
  final Color color;
  final Color shadowColor;
  final Color textColor;
  const LeoMathButton(this.text, this.onTap,
      {Key? key,
      this.color = const Color(0xFFfef200),
      this.shadowColor = const Color(0xFFffd742),
      this.textColor = const Color(0xFFa67c06)})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: onTap,
      child: Container(
        decoration: BoxDecoration(
          color: shadowColor,
          borderRadius: BorderRadius.circular(
            getProportionateHeight(30),
          ),
        ),
        padding: EdgeInsets.only(bottom: getProportionateHeight(8)),
        child: Container(
          width: double.infinity,
          height: getProportionateHeight(72),
          decoration: BoxDecoration(
            color: color,
            borderRadius: BorderRadius.circular(
              getProportionateHeight(30),
            ),
          ),
          child: Center(
              child: TextWidget(
            text,
            color: textColor,
            fontWeight: FontWeight.w700,
            size: getProportionateHeight(24),
          )),
        ),
      ),
    );
  }
}
