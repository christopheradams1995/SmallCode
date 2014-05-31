#include <iostream>

using namespace std;

//function declaration
void initcards();

struct playingCard
{
    string suit;
    string name;
    int value;
}card;

// Clubs 0-12 , Diamonds 13-25 , Hearts 26-38 , Spades 39-51
// Ace,2,3,4,5,6,7,8,9,10,Jack,Queen,King
playingCard cards[52];

int main()
{
    bool isEndProgram = false;
    initcards();
    std::cout << cards[13].name << " of " << cards[13].suit << std::endl;
    return 0;
}

void initcards()
{

    for(int i =0;i<52;i++)
    {
        if((i % 13) == 1)
        {
            cards[i].name = "Ace";
            cards[i].value = 11;
        }
        else if((i % 13) == 2)
        {
            cards[i].name = "two";
            cards[i].value = 2;
        }
        else if((i % 13) == 3)
        {
            cards[i].name = "three";
            cards[i].value = 3;
        }
        else if((i % 13) == 4)
        {
            cards[i].name = "three";
            cards[i].value = 3;
        }
        else if((i % 13) == 5)
        {
            cards[i].name = "four";
            cards[i].value = 4;
        }
        else if((i % 13) == 6)
        {
            cards[i].name = "five";
            cards[i].value = 5;
        }
        else if((i % 13) == 7)
        {
            cards[i].name = "six";
            cards[i].value = 6;
        }
        else if((i % 13) == 8)
        {
            cards[i].name = "seven";
            cards[i].value = 7;
        }
        else if((i % 13) == 8)
        {
            cards[i].name = "eight";
            cards[i].value = 8;
        }
        else if((i % 13) == 9)
        {
            cards[i].name = "nine";
            cards[i].value = 9;
        }
        else if((i % 13) == 10)
        {
            cards[i].name = "ten";
            cards[i].value = 10;
        }
        else if((i % 13) == 11)
        {
            cards[i].name = "jack";
            cards[i].value = 10;
        }
        else if((i % 13) == 12)
        {
            cards[i].name = "Queen";
            cards[i].value = 10;
        }
        else if((i % 13) == 0)
        {
            cards[i].name = "King";
            cards[i].value = 10;
        }

        if(i<13)
        {
            cards[i].suit = "Clubs";
        }
        else if(i<26)
        {
            cards[i].suit = "Diamonds";
        }
        else if(i<39)
        {
            cards[i].suit = "Hearts";
        }
        else
        {
            cards[i].suit = "Spades";
        }
    }
}

