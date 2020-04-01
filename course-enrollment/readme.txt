В этом случае используется техника тестирования класса с состояниями

Необходимо выделить состояния
Воспользуемся сервисом https://www.planttext.com/
Исходный код для построения ниже(также будет скрин диаграммы в корне проекта):

@startuml
hide empty description

[*]-->CourseIsNotFull

CourseIsNotFull --> CourseIsNotFull :enroll(enrolled) /do nothing\n\
enroll(unenrolled)[#students < max-1] / enroll\n\
unenroll(unenrolled) / do nothing\n\
unenroll(enrolled)/unenroll

CourseIsNotFull --> CourseIsFull :\
enroll(unenrolled)[#students == max-1]/enroll\n\

CourseIsFull --> CourseIsNotFull:\
unenroll(enrolled)/ unenroll

CourseIsFull --> CourseIsFull:\
enroll(enrolled) /do nothing \n\
unenroll(unenrolled)/do nothing\n\

CourseIsFull --> WaitingListIsNotEmpty:\
enroll(unenrolled)/ make waiting\n\

WaitingListIsNotEmpty --> CourseIsFull:\
unenroll(enrolled)[#waiting == 1]/\
unenroll enrolled and make first waiting enrolled\n\
unenroll(waiting)[waiting == 1]/\
unenroll waiting

WaitingListIsNotEmpty --> WaitingListIsNotEmpty:\n\
enroll(enrolled)/do nothing\n\
enroll(waiting)/do nothing\n\
unenroll(unenrolled)/do nothing\n\
unenroll(waiting)[#waiting > 1] /unenroll waiting\n\
unenroll(enrolled)[#waiting > 1]/\
unenroll enrolled and make first waiting enrolled

@enduml