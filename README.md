# anudeep-cmpe-202

Describe what is the primary problem you try to solve.

We must categorize the credit card into a specific category and confirm it based on the specifications given, such as the first digits and the total number of digits for different types of credit cards.

2. Describe what are the secondary problems you try to solve (if there are any).
We must initialize the credit card objects appropriately based on the validation tests carried out in the step above. We should create an output file in the requested format with entries containing the credit card type, credit card number, and any error messages when given an input file containing a variety of credit card numbers. Error messages are displayed when there are non-numeric characters in credit card number provided in the input file.

3. Describe what design pattern(s) you use how (use plain text and diagrams).
Since this problem involves the construction of particular objects based on the input type, I decided to utilize the factory pattern to solve it.
● The client requests the writing of a particular kind of output file and provides an input file containing credit card numbers.
● A new RecordIO object is created. A suitable _filetype_RecordIO object is then constructed in the RecordIOFactory depending on the input filename given by the client.
● There is logic in certain _filetype_RecordIO subclasses (CsvRecordIO, JsonRecordIO, and XmlRecordIO) to read the records in the input file using the proper readers and write the contents to an output file.
● Additionally, the CreditCardFactory class generates a new object of the appropriate type of credit card.
● By validating the inputs in specific subclasses(AmExCC, DiscoverCC, MasterCC, VisaCC), different types of credit cards are created.
● Using the OutputRecord class, each output record in the output file is created.

Credit Card Object Instantiation Class Diagram:
(Credit_Card_Object_Instantiation_Class_Diagram.png)

### CSV
```
✗ java -cp out/artifacts/anudeep_cmpe_202_jar/anudeep-cmpe-202.jar com.anudeep.Main src/main/resources/input.csv output/output.csv
Output is written to output.csv in output folder
```
```
✗ cat output.csv
CardNumber,CardType
5567894523129089,MasterCard
59012345678901234567890,"Invalid: more than 19 digits"
4123456789123,Visa
347856341908126,AmericanExpress
6011111100007756,Discover
3601112345678789,"Invalid: not a possible card number"
5167894523129089,MasterCard
4123456789123999,Visa
377856341908126,AmericanExpress
5367894523129089,MasterCard
"6011*890HJrt6789","Invalid: non numeric characters"
,"Invalid: empty/null card number"

```

### JSON
```
✗ java -cp out/artifacts/anudeep_cmpe_202_jar/anudeep-cmpe-202.jar com.anudeep.Main src/main/resources/input.json output/output.json
Output is written to output.csv in output folder
```
```
✗ cat output.json
[
    {
      "cardType": "MasterCard",
      "cardNumber": "5567894523129089"
    },
    {
      "cardType": "Invalid: more than 19 digits",
      "cardNumber": "59012345678901234567890"
    },
    {
      "cardType": "Visa",
      "cardNumber": "4123456789123"
    },
    {
      "cardType": "AmericanExpress",
      "cardNumber": "347856341908126"
    },
    {
      "cardType": "Discover",
      "cardNumber": "6011111100007756"
    },
    {
      "cardType": "Invalid: not a possible card number",
      "cardNumber": "3601112345678789"
    },
    {
      "cardType": "MasterCard",
      "cardNumber": "5167894523129089"
    },
    {
      "cardType": "Visa",
      "cardNumber": "4123456789123999"
    },
    {
      "cardType": "AmericanExpress",
      "cardNumber": "377856341908126"
    },
    {
      "cardType": "MasterCard",
      "cardNumber": "5367894523129089"
    },
    {
      "cardType": "Invalid: non numeric characters",
      "cardNumber": "6011*890HJrt6789"
    },
    {
      "cardType": "Invalid: empty/null card number",
      "cardNumber": ""
    }
  ]
```

### XML
```
✗ java -cp out/artifacts/anudeep_cmpe_202_jar/anudeep-cmpe-202.jar com.anudeep.Main src/main/resources/input.xml output/output.xml
Output is written to output.csv in output folder
```
```
<ArrayList>
  <item>
    <CardNumber>5567894523129089</CardNumber>
    <CardType>MasterCard</CardType>
  </item>
  <item>
    <CardNumber>59012345678901234567890</CardNumber>
    <CardType>Invalid: more than 19 digits</CardType>
  </item>
  <item>
    <CardNumber>4123456789123</CardNumber>
    <CardType>Visa</CardType>
  </item>
  <item>
    <CardNumber>347856341908126</CardNumber>
    <CardType>AmericanExpress</CardType>
  </item>
  <item>
    <CardNumber>6011111100007756</CardNumber>
    <CardType>Discover</CardType>
  </item>
  <item>
    <CardNumber>3601112345678789</CardNumber>
    <CardType>Invalid: not a possible card number</CardType>
  </item>
  <item>
    <CardNumber>5167894523129089</CardNumber>
    <CardType>MasterCard</CardType>
  </item>
  <item>
    <CardNumber>4123456789123999</CardNumber>
    <CardType>Visa</CardType>
  </item>
  <item>
    <CardNumber>377856341908126</CardNumber>
    <CardType>AmericanExpress</CardType>
  </item>
  <item>
    <CardNumber>5367894523129089</CardNumber>
    <CardType>MasterCard</CardType>
  </item>
  <item>
    <CardNumber>6011*890HJrt6789</CardNumber>
    <CardType>Invalid: non numeric characters</CardType>
  </item>
  <item>
    <CardNumber></CardNumber>
    <CardType>Invalid: empty/null card number</CardType>
  </item>
</ArrayList>
```

## Validations
### Validation that required number of arguments are passed
```
✗ java -cp anudeep-cmpe-202.jar com.anudeep.Main
Invalid syntax. Usage: java com.anudeep.Main <input file path> <output file path>
```

### Validation that input and output file extensions are same
```
✗ java -cp anudeep-cmpe-202.jar com.anudeep.Main src/main/resources/input.csv output/output.xml
Exception in thread "main" java.lang.RuntimeException: Input and output extensions should be the same
```
### Validation that input file path exists
```
✗ java -cp anudeep-cmpe-202.jar com.anudeep.Main input.csv output/output.csv
Exception in thread "main" java.lang.RuntimeException: Input path does not exist
```