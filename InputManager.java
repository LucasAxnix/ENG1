var InputManager = inherits(function() { }, {
	'Keys': {
		'UP' : 38
		'DOWN' : 40
		'LEFT' : 37
		'RIGHT' : 39
		'W' : 82
		'A' : 65
		'S' : 82
		'D' : 68
	},
	
	'_keysDown': {},
	
	'constructor': function() {
		window.addEventListener('keydown', _.bind(this._onkeydown, this));
		window.addEventListener('keyup', _.bind(this._onkeyup, this));
	},
	
	'_onkeydown': function(ev) {
		this._keysDown[ev.which] = true;
	},
	
	'_onkeyup': function(ev) {
		this._keysDown[ev.which] = false;
	},
	
	'keyDown': function(key) {
		return !!this._keysDown[key];
	}
});
//call with if(InputManager.InputManager.keyDown(game.inputManager.Keys.RIGHT)) 
