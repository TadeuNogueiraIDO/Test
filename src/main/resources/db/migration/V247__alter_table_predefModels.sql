alter table pre_def_model add column classification int8;
update pre_def_model set classification = 1 where name = 'NEWWHITE';
update pre_def_model set classification = 2 where name = 'NEWMARINE';
update pre_def_model set classification = 3 where name = 'SUMMER';
update pre_def_model set classification = 4 where name = 'SCRIBBLE';
update pre_def_model set classification = 5 where name = 'AURORA';
update pre_def_model set classification = 6 where name = 'NEWCITRIC';
update pre_def_model set classification = 7 where name = 'MURAL';
update pre_def_model set classification = 8 where name = 'STELLAR';
update pre_def_model set classification = 9 where name = 'NEWREFLECTION';
update pre_def_model set classification = 10 where name = 'LOLLIPOP';
update pre_def_model set classification = 11 where name = 'INFLUENCER';
update pre_def_model set classification = 12 where name = 'MYMUSIC';
update pre_def_model set classification = 13 where name = 'LINENEEDLE';
update pre_def_model set classification = 14 where name = 'PORTFOLIO';
update pre_def_model set classification = 15 where name = 'RAINBOW';
update pre_def_model set classification = 16 where name = 'URBAN';
update pre_def_model set classification = 17 where name = 'HEALTH';