import 'package:flutter/material.dart';
import 'package:horizontal_picker/horizontal_picker.dart';
import 'package:leomath/count_eggs.dart';
import 'package:leomath/login.dart';
import 'package:leomath/size_config.dart';
import 'package:leomath/text_widget.dart';
import 'package:leomath/user_provider.dart';
import 'package:provider/provider.dart';

class OnBoardingScreen extends StatefulWidget {
  const OnBoardingScreen({Key? key}) : super(key: key);

  @override
  State<OnBoardingScreen> createState() => _OnBoardingScreenState();
}

class _OnBoardingScreenState extends State<OnBoardingScreen> {
  late UserProvider userProvider;
  var isBoyOrGirl = false;
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    userProvider = Provider.of(context, listen: false);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: EdgeInsets.symmetric(
          horizontal: getProportionateWidth(36),
          vertical: getProportionateHeight(24),
        ),
        child: Column(
          children: [
            Row(
              children: [
                IconButton(
                    onPressed: () {
                      Navigator.of(context).pop();
                    },
                    icon: Icon(Icons.arrow_back_rounded)),
                Spacer(),
                TextWidget(
                  "Create Profile",
                  fontWeight: FontWeight.w600,
                  color: Colors.black.withOpacity(0.6),
                  size: getProportionateHeight(24),
                ),
                Spacer(),
              ],
            ),
            SizedBox(
              height: getProportionateHeight(24),
            ),
            Row(
              children: [
                Expanded(
                  child: Opacity(
                    opacity: isBoyOrGirl ? 0.5 : 1,
                    child: InkWell(
                      child: Image.asset("assets/boy_button.png"),
                      onTap: () {
                        setState(() {
                          isBoyOrGirl = false;

                          userProvider.setGender(GENDER.BOY);
                        });
                      },
                    ),
                  ),
                ),
                Expanded(
                  child: Opacity(
                    opacity: isBoyOrGirl ? 1 : 0.5,
                    child: InkWell(
                      child: Image.asset("assets/girl_button.png"),
                      onTap: () {
                        setState(() {
                          isBoyOrGirl = true;
                          userProvider.setGender(GENDER.GIRL);
                        });
                      },
                    ),
                  ),
                ),
              ],
            ),
            Spacer(),
            Image.asset(
              "assets/leo_cycling.png",
              width: double.infinity,
              fit: BoxFit.fitWidth,
            ),
            Spacer(),
            Center(
              child: HorizontalPicker(
                minValue: 3,
                maxValue: 8,
                divisions: 5,
                showCursor: false,
                onChanged: (num) {
                  userProvider.setAge(num);
                },
                activeItemTextColor: Colors.blue,
                height: getProportionateHeight(150),
                backgroundColor: Colors.transparent,
              ),
            ),
            SizedBox(
              height: getProportionateHeight(24),
            ),
            LeoMathButton(
              "Register",
              () {
                Navigator.of(context)
                    .push(MaterialPageRoute(builder: (context) => CountEggs()));
              },
              color: Color(0xFF69c1fe),
              shadowColor: Color(0xFF019eff),
              textColor: Colors.white,
            )
          ],
        ),
      ),
    );
  }
}
