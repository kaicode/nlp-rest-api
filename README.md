# NLP REST API
A RESTful API for Natural Language Processing, wrapping functionality from the Standford Core NLP library.

## Parse Text Endpoint
### Request
`POST /parse?text=<your-text>`

### Response
The response is a list of sentances each with:
- *text* - the original text of the sentence
- *posTags* - a list of POS tags identified in the sentence
- *constituencyParse* - a Constituency Parse Tree where each node contains a *label* and list of *children*

###Example
Request  
`curl -XPOST localhost:8080/parse?text=The%20quick%20brown%20fox%20jumped%20over%20the%20lazy%20dog`

Response  
```json
[
  {
    "text": "The quick brown fox jumped over the lazy dog",
    "posTags": [
      "DT",
      "JJ",
      "JJ",
      "NN",
      "VBD",
      "IN",
      "DT",
      "JJ",
      "NN"
    ],
    "constituencyParse": {
      "category": "ROOT",
      "children": [
        {
          "category": "S",
          "children": [
            {
              "category": "NP",
              "children": [
                {
                  "tag": "DT",
                  "category": "DT",
                  "children": [
                    {
                      "tag": "DT",
                      "text": "The"
                    }
                  ]
                },
                {
                  "tag": "JJ",
                  "category": "JJ",
                  "children": [
                    {
                      "tag": "JJ",
                      "text": "quick"
                    }
                  ]
                },
                {
                  "tag": "JJ",
                  "category": "JJ",
                  "children": [
                    {
                      "tag": "JJ",
                      "text": "brown"
                    }
                  ]
                },
                {
                  "tag": "NN",
                  "category": "NN",
                  "children": [
                    {
                      "tag": "NN",
                      "text": "fox"
                    }
                  ]
                }
              ]
            },
            {
              "category": "VP",
              "children": [
                {
                  "tag": "VBD",
                  "category": "VBD",
                  "children": [
                    {
                      "tag": "VBD",
                      "text": "jumped"
                    }
                  ]
                },
                {
                  "category": "PP",
                  "children": [
                    {
                      "tag": "IN",
                      "category": "IN",
                      "children": [
                        {
                          "tag": "IN",
                          "text": "over"
                        }
                      ]
                    },
                    {
                      "category": "NP",
                      "children": [
                        {
                          "tag": "DT",
                          "category": "DT",
                          "children": [
                            {
                              "tag": "DT",
                              "text": "the"
                            }
                          ]
                        },
                        {
                          "tag": "JJ",
                          "category": "JJ",
                          "children": [
                            {
                              "tag": "JJ",
                              "text": "lazy"
                            }
                          ]
                        },
                        {
                          "tag": "NN",
                          "category": "NN",
                          "children": [
                            {
                              "tag": "NN",
                              "text": "dog"
                            }
                          ]
                        }
                      ]
                    }
                  ]
                }
              ]
            }
          ]
        }
      ]
    }
  }
]
```