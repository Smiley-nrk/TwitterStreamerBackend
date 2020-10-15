Technology Used:
- SpringBoot
- MongoDB
- ReactJs

Concepts Used:
- Reactive Webflux
- Reactive Embedded MongoDB
- Server Sent Events


TODO:
1. Mobile friendly (use Bootstrap and seperate view for small screen)
2. Multi user support
	2.1 update rules to all users every time anyone adds/deletes rule   OR   Make rules specific to session
3. Integrate SonarQube
4. Create Instructions Page
5. Implement JUnit testing
6. paginate tweets in front-end to 30-40 per page and append new tweets on top instead of on bottom
7. disable start button when stream is connected.
8. show the corresponding filter along with tweet. (i.e, which filter caused this tweet to show)
9. show the author of the tweet also








2. Multi user support
	- No tweets on page load.
	- on adding rule, simply update rule list and close tweet stream and open new SSE.
	- in new SSE, send rule list as cookie.
	- on server Side, compare matching rule. if its the same as cookie, then send it.
	- on cancel event, delete the rules mentioned in cookie.