
Logging Example


Obs:

- This may cause a NullPointer Exception and it's not doing nothing with the returned value

`` messageText.trim(); ``

- Better use this:

``
if(messageText == null || messageText.trim().isEmpty()){
            return;
        }
``

instead of

``
 if (messageText == null || messageText.length() == 0) { 
``


- Use enums instead of integer for types
- Mybatis is better than Java SQL Statements
- Using StringBuilder instead of String
- Constants don't bite, use them
- Builders is better for POJOs
