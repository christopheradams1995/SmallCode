#include "SDL.h"

int main ( int argc, char *argv[] )
{
  /* initialize SDL */
  SDL_Init(SDL_INIT_VIDEO);

  /* set the title bar */
  SDL_WM_SetCaption("SDL Test", "SDL Test");

  /* create window */
  SDL_Surface* screen = SDL_SetVideoMode(640, 480, 0, 0);
}