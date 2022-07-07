import 'package:flutter/material.dart';
import 'package:leomath/size_config.dart';
import 'package:leomath/success_screen.dart';
import 'package:leomath/text_widget.dart';

class CountEggs extends StatefulWidget {
  const CountEggs({Key? key}) : super(key: key);

  @override
  State<CountEggs> createState() => _CountEggsState();
}

class _CountEggsState extends State<CountEggs>
    with SingleTickerProviderStateMixin {
  late AnimationController _controller;

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(vsync: this);
  }

  @override
  void dispose() {
    super.dispose();
    _controller.dispose();
  }

  var totalQuestions = 5;
  var question = 0;

  var questionText = "Count the number of eggs";
  var options = [6, 7, 5];
  var answer = 6;

  changeQuestion() {
    if (question == 0) {
      questionText = "Count the number of eggs";
      options = [6, 7, 5];
      answer = 6;
    } else if (question == 1) {
      questionText = "Count the number of chicks";
      options = [5, 4, 3];
      answer = 3;
    } else if (question == 2) {
      questionText = "Count the number of chicks and eggs";
      options = [8, 9, 7];
      answer = 9;
    } else if (question == 4) {
      questionText = "Count the number of eggs in 2nd row";
      options = [2, 4, 3];
      answer = 2;
    } else if (question == 3) {
      questionText = "Count the number of rows in the chicken coop";
      options = [5, 4, 3];
      answer = 3;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          Image.asset("assets/count_eggs.png",
              width: double.infinity,
              height: double.infinity,
              fit: BoxFit.cover),
          Column(
            children: [
              SizedBox(
                height: getProportionateHeight(24),
              ),
              Row(
                children: [
                  SizedBox(
                    width: getProportionateWidth(24),
                  ),
                  Image.asset(
                    "assets/leo_face.png",
                    height: getProportionateHeight(64),
                  ),
                  SizedBox(
                    width: getProportionateWidth(8),
                  ),
                  Expanded(
                    child: Container(
                      decoration: BoxDecoration(
                          color: Color(0xFF7bcde8),
                          borderRadius: BorderRadius.circular(
                              getProportionateHeight(54))),
                      height: getProportionateHeight(42),
                      child: Row(
                        children: [
                          Expanded(
                            flex: question,
                            child: Container(
                              decoration: BoxDecoration(
                                  color: Color(0xFFffdb33),
                                  borderRadius: BorderRadius.circular(
                                      getProportionateHeight(54))),
                              height: getProportionateHeight(42),
                            ),
                          ),
                          Spacer(
                            flex: totalQuestions - question,
                          )
                        ],
                      ),
                    ),
                  ),
                  SizedBox(
                    width: getProportionateWidth(8),
                  ),
                  Image.asset(
                    "assets/pause.png",
                    height: getProportionateHeight(64),
                  ),
                  SizedBox(
                    width: getProportionateWidth(24),
                  ),
                ],
              ),
              SizedBox(
                height: getProportionateHeight(48),
              ),
              Container(
                padding: EdgeInsets.symmetric(
                    vertical: getProportionateHeight(8),
                    horizontal: getProportionateWidth(24)),
                decoration: BoxDecoration(
                    color: Color(0xFFcaf1fd),
                    borderRadius: BorderRadius.circular(28)),
                child: TextWidget(
                  questionText,
                  fontWeight: FontWeight.bold,
                  color: Colors.black.withOpacity(0.7),
                ),
              ),
              Spacer(),
              Container(
                height: getProportionateHeight(100),
                margin: EdgeInsets.symmetric(
                    horizontal: getProportionateWidth(32),
                    vertical: getProportionateHeight(24)),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(28),
                  color: Color(0xFFc2e87c),
                ),
                child: Row(children: [
                  Expanded(
                    child: InkWell(
                      onTap: () {
                        setState(() {
                          if (options[0] == answer) {
                            if (question < totalQuestions - 1) {
                              question++;
                              changeQuestion();
                            } else {
                              Navigator.of(context).push(
                                MaterialPageRoute(
                                  builder: (context) => const SuccessScreen(),
                                ),
                              );
                            }
                          } else {
                            const snackBar = SnackBar(
                              content: Text('Oops! Wrong answer'),
                            );
                            ScaffoldMessenger.of(context)
                                .showSnackBar(snackBar);
                          }
                        });
                      },
                      child: Container(
                        height: getProportionateHeight(100),
                        margin: EdgeInsets.symmetric(
                            horizontal: getProportionateWidth(16),
                            vertical: getProportionateHeight(16)),
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(16),
                          color: Colors.white,
                        ),
                        child: Center(
                          child: TextWidget(
                            options[0].toString(),
                            color: Color(0xFFcb95f4),
                            size: getProportionateHeight(42),
                          ),
                        ),
                      ),
                    ),
                  ),
                  Expanded(
                    child: InkWell(
                      onTap: () {
                        setState(() {
                          if (options[1] == answer) {
                            if (question < totalQuestions - 1) {
                              question++;
                              changeQuestion();
                            } else {
                              Navigator.of(context).push(
                                MaterialPageRoute(
                                  builder: (context) => const SuccessScreen(),
                                ),
                              );
                            }
                          } else {
                            const snackBar = SnackBar(
                              content: Text('Oops! Wrong answer'),
                            );
                            ScaffoldMessenger.of(context)
                                .showSnackBar(snackBar);
                          }
                        });
                      },
                      child: Container(
                        height: getProportionateHeight(100),
                        margin: EdgeInsets.symmetric(
                            horizontal: getProportionateWidth(16),
                            vertical: getProportionateHeight(16)),
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(16),
                          color: Colors.white,
                        ),
                        child: Center(
                          child: TextWidget(
                            options[1].toString(),
                            color: Color(0xFF7ac2ff),
                            size: getProportionateHeight(42),
                          ),
                        ),
                      ),
                    ),
                  ),
                  Expanded(
                    child: InkWell(
                      onTap: () {
                        setState(() {
                          if (options[2] == answer) {
                            if (question < totalQuestions - 1) {
                              question++;
                              changeQuestion();
                            } else {
                              Navigator.of(context).push(
                                MaterialPageRoute(
                                  builder: (context) => const SuccessScreen(),
                                ),
                              );
                            }
                          } else {
                            const snackBar = SnackBar(
                              content: Text('Oops! Wrong answer'),
                            );
                            ScaffoldMessenger.of(context)
                                .showSnackBar(snackBar);
                          }
                        });
                      },
                      child: Container(
                        height: getProportionateHeight(100),
                        margin: EdgeInsets.symmetric(
                            horizontal: getProportionateWidth(16),
                            vertical: getProportionateHeight(16)),
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(16),
                          color: Colors.white,
                        ),
                        child: Center(
                          child: TextWidget(
                            options[2].toString(),
                            fontWeight: FontWeight.bold,
                            color: Color(0xFFff96de),
                            size: getProportionateHeight(42),
                          ),
                        ),
                      ),
                    ),
                  )
                ]),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
